package br.com.projeto.lp2.controller.service;

import br.com.projeto.lp2.controller.service.request.ServiceRequest;
import br.com.projeto.lp2.controller.service.response.ServiceResponse;
import br.com.projeto.lp2.core.domain.Service;
import br.com.projeto.lp2.core.ports.driver.service.CreateServicePort;
import br.com.projeto.lp2.core.ports.driver.service.DeleteServiceByIdPort;
import br.com.projeto.lp2.core.ports.driver.service.GetServiceByIdPort;
import br.com.projeto.lp2.core.ports.driver.service.UpdateServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("services")
public record ServiceController(
        CreateServicePort createServicePort,
        GetServiceByIdPort getServiceByIdPort,
        UpdateServicePort  updateServicePort,
        DeleteServiceByIdPort deleteServiceByIdPort

) {


    @PostMapping
    public ResponseEntity<ServiceResponse> post(@RequestBody ServiceRequest request, HttpServletResponse response) {
        var service = request.toService();
        var serviceSaved = createServicePort.apply(service);
        var serviceResponse = new ServiceResponse().fromService(serviceSaved);

        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequestUri().path("{/id}").buildAndExpand(serviceSaved.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(serviceResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServiceResponse> getUserById(@PathVariable String id) {
        return getServiceByIdPort.apply(id)
                .map(service -> ResponseEntity.ok(new ServiceResponse().fromService(service)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<ServiceResponse> updateUser(@PathVariable String id, @RequestBody ServiceRequest request) {
        if(request == null || id == null) {
            throw new IllegalArgumentException("Erro ao atualizar o usuario");
        }
        Service service = request.toService();
        Optional<Service> userOptional = updateServicePort.apply(id, service);
        return userOptional.map(value -> ResponseEntity.ok(new ServiceResponse().fromService(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        getServiceByIdPort.apply(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        deleteServiceByIdPort.apply(id);
    }
}
