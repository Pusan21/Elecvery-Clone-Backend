package com.Pusan21.ElecveryCloneBackend.service;

import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.AddMyCarDto;
import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.GetMyCarDto;
import com.Pusan21.ElecveryCloneBackend.dto.MyCarDto.UpdateMyCarDto;
import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.entity.MyCar;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import com.Pusan21.ElecveryCloneBackend.repository.MyCarRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyCarService {

  private final MemberRepository memberRepository;

  private final MyCarRepository myCarRepository;

  public List<GetMyCarDto> getMyCars(Long memberId) {
    Member member = memberRepository.findById(memberId).orElse(null);
    List<MyCar> myCars = myCarRepository.findAllByMember(member);
    return myCars.stream().map(GetMyCarDto::from).toList();
  }

  public Long addMyCar(Long memberId, AddMyCarDto requestDto) {
    Member member = memberRepository.findById(memberId).orElse(null);
    return myCarRepository.save(requestDto.toEntity(member))
        .getMyCarNumber();
  }

  public UpdateMyCarDto updateMyCar(Long memberId, Long myCarsId, UpdateMyCarDto requestDto) {
    Member member = memberRepository.findById(memberId).orElse(null);
    MyCar myCar = myCarRepository.findByMemberAndAndMyCarNumber(member, myCarsId);
    myCar.updateMyCar(requestDto);

    return UpdateMyCarDto.from(myCarRepository.save(myCar));
  }

  public Long deleteMyCar(Long memberId, Long myCarsId) {
    Member member = memberRepository.findById(memberId).orElse(null);
    MyCar myCar = myCarRepository.findByMemberAndAndMyCarNumber(member, myCarsId);
    myCarRepository.delete(myCar);

    return myCar.getMyCarNumber();
  }
}
