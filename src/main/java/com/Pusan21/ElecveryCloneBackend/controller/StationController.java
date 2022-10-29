package com.Pusan21.ElecveryCloneBackend.controller;

import com.Pusan21.ElecveryCloneBackend.service.StationService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stations")
public class StationController {

  private final StationService stationService;

  @GetMapping("/charger-info")
  public String getChargerInfo() throws Exception {
    StringBuilder result = new StringBuilder();
    String urlString = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo?"
        + "ServiceKey=mAs3frtve8U45TsTneCa4or8sREDMReOfzbLUkmwIuEnmCMnMDZhpJlUeZ69%2Fta2IPeS9a8Ueal9sKhuunU3JQ%3D%3D"
        + "&pageNo=1"
        + "&numOfRows=100";

    URL url = new URL(urlString);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setRequestMethod("GET");

    BufferedReader br = new BufferedReader(
        new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

    String returnLine;

    while ((returnLine = br.readLine()) != null) {
      result.append(returnLine + "\n\r");
    }

    JSONObject jsonObject = XML.toJSONObject(result.toString());
    String jsonPrintString = jsonObject.toString();

    urlConnection.disconnect();

    stationService.saveChargerInfo(jsonObject);

    return jsonPrintString;
  }

  @GetMapping("/charger-status")
  public String getChargerStatus() throws Exception {
    StringBuilder result = new StringBuilder();

    String urlString = "http://apis.data.go.kr/B552584/EvCharger/getChargerStatus?"
        + "ServiceKey=mAs3frtve8U45TsTneCa4or8sREDMReOfzbLUkmwIuEnmCMnMDZhpJlUeZ69%2Fta2IPeS9a8Ueal9sKhuunU3JQ%3D%3D"
        + "&pageNo=1"
        + "&numOfRows=100";

    URL url = new URL(urlString);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setRequestMethod("GET");

    BufferedReader br = new BufferedReader(
        new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

    String returnLine;

    while ((returnLine = br.readLine()) != null) {
      result.append(returnLine + "\n\r");
    }

    JSONObject jsonObject = XML.toJSONObject(result.toString());
    String jsonPrintString = jsonObject.toString();

    urlConnection.disconnect();

    return jsonPrintString;
  }
}
