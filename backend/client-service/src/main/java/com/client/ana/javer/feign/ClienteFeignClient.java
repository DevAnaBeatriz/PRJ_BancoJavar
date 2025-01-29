package com.client.ana.javer.feign;

import com.client.ana.javer.config.FeignClientConfig;
import com.client.ana.javer.dto.ClienteDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "clienteFeignClient",
        url = "http://localhost:8080/api/clientes",
        configuration = FeignClientConfig.class
)
public interface ClienteFeignClient {

    @GetMapping
    List<ClienteDTO> getAllClientes();

    @PostMapping
    ClienteDTO createCliente(@RequestBody ClienteDTO clienteDTO);

    @PutMapping("/{id}")
    ClienteDTO updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO);

    @DeleteMapping("/{id}")
    void deleteCliente(@PathVariable Long id);
}
