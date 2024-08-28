//package am.fillandgo.util.dataMappers;
//
//import am.fillandgo.dto.requests.SaveUserRequestDto;
//import am.fillandgo.dto.requests.UpdateUserDto;
//import am.fillandgo.dto.responses.UserDto;
//import am.fillandgo.models.User;
//import am.fillandgo.util.generators.RandomStringGeneratorUtil;
//
///**
// * UserMapper is a utility class that provides mapping methods between User and UserDto objects.
// */
//public class UserMapper {
//
//    private UserMapper() {
//    }
//
//    /**
//     * Maps a User object to a UserDto object.
//     * @param user The User object to be mapped to UserDto.
//     * @return The mapped UserDto object.
//     */
//    public static UserDto mapToUserDto(User user) {
//
//        return UserDto.builder()
//                .id(user.getId())
//                .userName(user.getUserName())
//                .email(user.getEmail())
//                .build();
//
//    }
//
//    /**
//     * Maps a SaveUserRequestDto object to a User object.
//     * @param saveUserRequestDto The SaveUserRequestDto object to be mapped.
//     * @return The mapped User object.
//     */
//    public static User mapToUser(SaveUserRequestDto saveUserRequestDto) {
//
//        return User.builder()
//                .id(RandomStringGeneratorUtil.uuId())
//                .userName(saveUserRequestDto.getUserName())
//                .email(saveUserRequestDto.getEmail())
//                .password(saveUserRequestDto.getPassword())
//                .role(saveUserRequestDto.getRole() == null ? "USER" : saveUserRequestDto.getRole())
//                .build();
//
//    }
//
//    /**
//     * Maps an UpdateUserDto object to a User object.
//     * @param updateUserRequestDto The UpdateUserDto object to be mapped.
//     * @return A User object with updated user data.
//     */
//    public static User mapToUpdateUser(UpdateUserDto updateUserRequestDto) {
//
//        return User.builder()
//                .email(updateUserRequestDto.getEmail())
//                .userName(updateUserRequestDto.getUserName())
//                .password(updateUserRequestDto.getPassword())
//                .build();
//
//    }
//}