package com.Pusan21.ElecveryCloneBackend.service;

import com.Pusan21.ElecveryCloneBackend.entity.Station;
import com.Pusan21.ElecveryCloneBackend.entity.StationLocation;
import com.Pusan21.ElecveryCloneBackend.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StationService {

  private final StationRepository stationRepository;

  public void saveChargerInfo(JSONObject jsonObject) {
    JSONObject response = jsonObject.getJSONObject("response");
    JSONObject body = response.getJSONObject("body");
    JSONObject items = body.getJSONObject("items");
    JSONArray item = items.getJSONArray("item");

    for (int i = 0; i < item.length(); i++) {
      JSONObject object = item.getJSONObject(i);
      Station station = Station.builder()
          .stationName(object.getString("statNm"))
          .registrationNumber(null)
          .useTime(object.getString("useTime"))
          .stateUpdateDate(object.getLong("statUpdDt"))
          .note(object.getString("note"))
          .availableChargeOption(object.getString("chgerType"))
          .output(object.getLong("output"))
          .method(object.getString("method"))
          .build();

      StationLocation stationLocation = StationLocation.builder()
          .address(object.getString("addr"))
          .latitude(object.getDouble("lat"))
          .longitude(object.getDouble("lng"))
          .zcode(object.getLong("zcode"))
          .zscode(object.getLong("zscode"))
          .build();

      station.setStationLocation(stationLocation);
      stationRepository.save(station);
    }
  }
}
