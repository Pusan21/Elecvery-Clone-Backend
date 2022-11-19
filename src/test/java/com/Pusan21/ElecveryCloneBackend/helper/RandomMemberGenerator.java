package com.Pusan21.ElecveryCloneBackend.helper;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.repository.MemberRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class RandomMemberGenerator {

  public static Member generateTo(MemberRepository repository, String nickname) {
    var createDateTime = generateZonedDateTime();
    var lastLoginDateTime = generateZonedDateTime();

    if (createDateTime.isAfter(lastLoginDateTime)) {
      var temp = createDateTime;
      createDateTime = lastLoginDateTime;
      lastLoginDateTime = temp;
    }

    var bytes = new byte[8];
    new Random().nextBytes(new byte[60]);
    var password = new String(bytes);

    Member member =
        new Member(
            nickname,
            password,
            nickname + "@gmail.com",
            nickname,
            createDateTime,
            lastLoginDateTime
        );
    repository.save(member);

    return member;
  }

  private static ZonedDateTime generateZonedDateTime() {
    var begin = ZonedDateTime.now().minusYears(1);
    var end = ZonedDateTime.now().plusYears(1);
    var epochSecond = new Random().nextLong(
        begin.toInstant().getEpochSecond(),
        end.toInstant().getEpochSecond()
    );
    return ZonedDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.of("Asia/Seoul"));
  }
}
