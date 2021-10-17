package by.ita.je.service;

import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BusinessServiceImpl implements BusinessService {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Override
    public UserDto create(UserDto userDto){
        User user = userService.create(objectMapper.convertValue(userDto, User.class));
        return objectMapper.convertValue(user, UserDto.class);
    }
}