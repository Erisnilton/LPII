package br.edu.ifce.lp2.controller;

import br.edu.ifce.lp2.controller.request.PublisherRequest;
import br.edu.ifce.lp2.controller.response.PublisherResponse;
import br.edu.ifce.lp2.model.entities.Publisher;
import br.edu.ifce.lp2.model.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publishers")
public class PublisherController {

    @Autowired
    private PublisherService service;

    @PostMapping
    public PublisherResponse post(@RequestBody PublisherRequest request) {
        var publisher = request.toPublisher();
        return new PublisherResponse().fromPublisher(service.create(publisher));
    }

    @PutMapping("{id}")
    public PublisherResponse put(@PathVariable String id, @RequestBody PublisherRequest request) {
        var publisher = request.toPublisher();
        return new PublisherResponse().fromPublisher(service.update(id, publisher));
    }

    @GetMapping
    public Page<PublisherResponse> get(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int linesPerPage,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "name") String orderBy
    ) {

        var pageable = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);

        return service.getAll(pageable)
                .map(p -> new PublisherResponse().fromPublisher(p));
    }

    @GetMapping("{id}")
    public Publisher getById(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

}
