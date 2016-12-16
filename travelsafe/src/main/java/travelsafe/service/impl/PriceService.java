package travelsafe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travelsafe.model.Price;
import travelsafe.repository.PriceRepository;
import travelsafe.service.GenericService;

import java.util.List;

/**
 * Created by Dorian on 11/25/2016.
 */
@Service
@Transactional
public class PriceService implements GenericService<Price> {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Price> getAll() {
        return priceRepository.findAll();
    }

    @Override
    public Price save(Price entity) {
        return null;
    }

}
