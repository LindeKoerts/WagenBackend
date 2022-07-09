package com.wagengaragebackend.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CarJobRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarJobRepository carJobRepository;

/*  //WERKT NIET
    @Test
    void whenFindByStatusThenReturnCarjob(){

        CarJob job = new CarJob( );
        job.setStatus(CarJobStatus.PLANNED);
        entityManager.persist(job);
        entityManager.flush();
        CarJob found = (CarJob) carJobRepository.findByStatus(CarJobStatus.PLANNED);

        assertThat(found.getStatus()).isEqualTo(CarJobStatus.PLANNED);
    }

    //test gebruik 2zoekvariabelen als key returns juiste carJob
    @Test
    public void whenFindByStatusAndCustomerNameAndCustomerEmailThenReturnCarJob(){

        CarJob job = new CarJob( );
        Customer customer = new Customer("jansen", "jansen@mail", "1234");
        Car car = new Car("123AB", "auto");
        job.setCar(car);
        job.setCustomer(customer);
        job.setStatus(CarJobStatus.COMPLETED);

        CarJob otherJob = new CarJob();
        Customer otherCustomer = new Customer("jansen", "other@mail", "5678");
        otherJob.setCustomer(otherCustomer);

        LocalDateTime date = LocalDateTime.of(2020, Month.JANUARY, 18, 10,30);
        job.setRepairDate(date);
        job.setRemarks("test");

        entityManager.persist(job);
        entityManager.flush();

        CarJob found = carJobRepository.findByStatusAndCustomerNameAndCustomerEmail(CarJobStatus.COMPLETED,"jansen", "jansen@mail");

        assertThat(found.getCustomer().getEmail()).isEqualTo("jansen@mail");
    }
 */
}





