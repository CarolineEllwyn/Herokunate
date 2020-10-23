package caroline.hibernationapp.hibernation.Repositories;

import caroline.hibernationapp.hibernation.Models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
