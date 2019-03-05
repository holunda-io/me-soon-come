package io.holunda.mesooncome.model.es;

import io.holunda.mesooncome.model.db.RequestBodyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBody {

    private List<Sort> sort;

    public static RequestBody from(RequestBodyEntity entity){

        return RequestBody.builder()
                .sort(entity.getSorts()
                .stream()
                .map(s->Sort.from(s))
                .collect(Collectors.toList()))
                .build();
    }

}
