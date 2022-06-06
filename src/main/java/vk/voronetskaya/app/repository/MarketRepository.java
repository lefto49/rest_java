package vk.voronetskaya.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vk.voronetskaya.app.model.Market;

public interface MarketRepository extends MongoRepository<Market, Integer> {
}
