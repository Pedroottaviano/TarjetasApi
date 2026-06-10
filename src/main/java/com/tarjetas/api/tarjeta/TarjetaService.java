package com.tarjetas.api.tarjeta;

import com.tarjetas.api.dolar.Dolar;
import com.tarjetas.api.dolar.DolarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private DolarClient dolarClient;

    public List<Tarjeta> getTarjetas() {
        return tarjetaRepository.findAll();
    }

    public Optional<Tarjeta> getTarjetaById(Long id) {
        return tarjetaRepository.findById(id);
    }

    public List<Tarjeta> getTarjetasByCliente(Long clienteId) {
        return tarjetaRepository.findByClienteId(clienteId);
    }

    public List<Tarjeta> getTarjetasByEstado(EstadoTarjeta estado) {
        return tarjetaRepository.findByEstado(estado);
    }

    public Tarjeta addTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    public Tarjeta updateEstado(Long id, EstadoTarjeta nuevoEstado) {
        Tarjeta tarjeta = tarjetaRepository.findById(id)
                .orElseThrow(() -> new TarjetaNotFoundException("Tarjeta no encontrada con id: " + id));
        tarjeta.setEstado(nuevoEstado);
        return tarjetaRepository.save(tarjeta);
    }

    public void deleteById(Long id) {
        tarjetaRepository.deleteById(id);
    }

    public Dolar getCotizacion() {
        return dolarClient.getDolarOficial();
    }
}
