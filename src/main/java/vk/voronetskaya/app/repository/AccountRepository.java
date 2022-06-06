package vk.voronetskaya.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vk.voronetskaya.app.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer> {
}
