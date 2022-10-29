package com.Pusan21.ElecveryCloneBackend.repository;

import static org.assertj.core.api.Assertions.*;

import com.Pusan21.ElecveryCloneBackend.entity.Station;
import com.Pusan21.ElecveryCloneBackend.helper.StationRepositoryTestHelper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public class StationRepositoryTest extends StationRepositoryTestHelper {

  @Test
  @DisplayName("충전소 저장 확인")
  public void saveStation() throws NotFoundException {
    //given
    Station station = generateStation(1L, "123-45-6789", "24시간 이용 가능", "elecvery");

    em.flush();
    em.clear();
    //when
    Station findStation = stationRepository.findById(station.getStationNumber())
        .orElseThrow(NotFoundException::new);

    //then
    assertThat(station.getStationNumber()).isEqualTo(findStation.getStationNumber());
    assertThat(station.getStationName()).isEqualTo(findStation.getStationName());
    assertThat(station.getRegistrationNumber()).isEqualTo(findStation.getRegistrationNumber());
    assertThat(station.getUseTime()).isEqualTo(findStation.getUseTime());
  }

  @Test
  @DisplayName("충전소 개수 확인")
  public void getNumberOfStations() {
    //given
    generateStation(1L, "123-45-6789", "24시간 이용가능", "elecvery1");
    generateStation(2L, "123-45-9999", "24시간 이용가능", "elecvery2");
    generateStation(3L, "123-54-6789", "24시간 이용가능", "elecvery3");

    em.flush();
    em.clear();
    //when
    List<Station> stationList = stationRepository.findAll();

    //then
    assertThat(stationList.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("충전소 삭제 확인")
  public void deleteStation() {
    //given
    generateStation(1L, "123-45-6789", "24시간 이용가능", "elecvery1");
    generateStation(2L, "123-45-9999", "24시간 이용가능", "elecvery2");
    Station deleteThis = generateStation(3L, "123-54-6789", "24시간 이용가능", "elecvery3");
    stationRepository.delete(deleteThis);

    em.flush();
    em.clear();
    //when
    List<Station> stationList = stationRepository.findAll();

    //then
    assertThat(stationList.size()).isEqualTo(2);
  }
}
