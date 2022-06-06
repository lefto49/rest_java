package vk.voronetskaya.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vk.voronetskaya.app.model.Market;

@Repository
public interface MarketRepository extends MongoRepository<Market, Integer> {
}
