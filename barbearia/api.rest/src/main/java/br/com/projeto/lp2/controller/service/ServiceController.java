package br.com.projeto.lp2.controller.service;

import br.com.projeto.lp2.controller.service.request.ServiceRequest;
import br.com.projeto.lp2.controller.service.response.ServiceResponse;
import br.com.projeto.lp2.controller.user.request.UserRequest;
import br.com.projeto.lp2.controller.user.response.UserResponse;
import br.com.projeto.lp2.core.ports.driver.service.CreateServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("services")
public record ServiceController(CreateServicePort createServicePort) {


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
}
