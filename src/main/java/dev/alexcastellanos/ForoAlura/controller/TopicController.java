package dev.alexcastellanos.ForoAlura.controller;

import dev.alexcastellanos.ForoAlura.domain.topic.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<ResponseTopicData> registerTopic(@RequestBody @Valid RegisterTopicData registerTopicData, UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicRepository.save(new Topic(registerTopicData));
        ResponseTopicData reponseTopicData = new ResponseTopicData(topic);
        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(reponseTopicData);
    }

    @GetMapping
    public ResponseEntity<Page<ListTopicData>> getAllTopics(@PageableDefault(size = 2) Pageable pagination){
        return ResponseEntity.ok(topicRepository.findAll(pagination).map(ListTopicData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTopicData> getTopicById(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        ResponseTopicData responseTopicData = new ResponseTopicData(topic);
        return ResponseEntity.ok(responseTopicData);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseTopicData> updateTopic(@RequestBody @Valid UpdateTopicData updateTopicData){
        Topic topic = topicRepository.getReferenceById(updateTopicData.id());
        topic.updateTopic(updateTopicData);
        ResponseTopicData responseTopicData = new ResponseTopicData(topic);
        return ResponseEntity.ok(responseTopicData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        topic.deleteTopic();
        return ResponseEntity.noContent().build();
    }
}
