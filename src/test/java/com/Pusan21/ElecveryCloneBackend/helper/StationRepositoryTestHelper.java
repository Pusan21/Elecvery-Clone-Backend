package com.Pusan21.ElecveryCloneBackend.helper;

import com.Pusan21.ElecveryCloneBackend.entity.Station;
import com.Pusan21.ElecveryCloneBackend.repository.StationRepository;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StationRepositoryTestHelper {

  @Autowired
  protected EntityManager em;
  @Autowired
  protected StationRepository stationRepository;

  protected Station generateStation(Long stationNumber, boolean isActive, String registrationNumber,
      String stationName) {
    return stationRepository.save(
        Station.builder()
            .stationNumber(stationNumber)
            .isActive(isActive)
            .registrationNumber(registrationNumber)
            .stationName(stationName)
            .build()
    );
  }
}
