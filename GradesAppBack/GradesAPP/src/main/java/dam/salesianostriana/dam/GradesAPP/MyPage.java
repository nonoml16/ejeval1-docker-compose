package dam.salesianostriana.dam.GradesAPP;


import com.fasterxml.jackson.annotation.JsonView;
import dam.salesianostriana.dam.GradesAPP.instrumento.jsonViews.InstrumentoViews;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;



@Builder
@JsonView(InstrumentoViews.InstrumentoList.class)
public record MyPage<T>(

        List<T> content,

        Integer size,

        Long totalElements,

        Integer pageNumber,

        boolean first,
        boolean last

)  {
    public static <T> MyPage<T> of(Page<T> page){
        return MyPage.<T>builder()
                .content(page.getContent())
                .first(page.isFirst())
                .last(page.isLast())
                .pageNumber(page.getPageable().getPageNumber())
                .size(page.getPageable().getPageSize())
                .totalElements(page.getTotalElements())
                .build();
    }
}
