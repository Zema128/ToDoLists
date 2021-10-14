package by.ita.je.model.api;

import by.ita.je.model.enams.Categories;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Component
@Converter(autoApply = true)
public class CategoriesConverter implements AttributeConverter<Categories, String> {

    @Override
    public String convertToDatabaseColumn(Categories categories) {
        if (categories == null){
            return null;
        }
        return categories.getCode();
    }

    @Override
    public Categories convertToEntityAttribute(String code) {
        if (code == null){
            return null;
        }
        return Stream.of(Categories.values())
                .filter(conditions -> conditions.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}