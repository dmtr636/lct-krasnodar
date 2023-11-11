package com.kydas.lctkrasnodar.courses;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserCourseDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long courseId;

    private String startTimestamp;

    private String finishTimestamp;

    private Integer rating;

    private Integer testScore;

    public UserCourseDTO(UserCourse userCourse) {
        id = userCourse.getId();
        userId = userCourse.getUserId();
        courseId = userCourse.getCourseId();
        startTimestamp = userCourse.getStartTimestampString();
        finishTimestamp = userCourse.getFinishTimestampString();
        rating = userCourse.getRating();
        testScore = userCourse.getTestScore();
    }
}