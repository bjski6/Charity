package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository <Donation, Long> {

    @Query(value = "select sum(quantity) from donation", nativeQuery = true)
        Integer sumQuantity ();

  @Query(value = "select sum(quantity) from donation where user_id=?1", nativeQuery = true)
  Long sumUserQuantity (Long id);

    @Query(value = "select count(*) from donation ", nativeQuery = true)
        Integer countDonation ();

    @Query(value = "select count(*) from donation where user_id=?1", nativeQuery = true)
    Long countUserDonation (Long id);

    @Query(value = "select * from donation_categories where donation_id=?1", nativeQuery = true)
    List<Category> categories (Long id);

}
