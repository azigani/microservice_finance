# Common Helper Module
This is the common helper module to share DTS's and other helper functions across all the microservices.

Folder structure
```bash
common
  |_src
    |_main
      |_java
        |_com.finpay.common
          |_dto
            |_accounts
            |_transactions
            |_users
          |_enums
          |_exception
          |_utils
          |_CommonServiceApplication.java
  |_pom.xml
  |_README.md
```
Just build the jar file and use it in other services by pom.xml
```bash
mvn clean install
```
#### Uses
Add this to other services in pom.xml
```xml
<!-- Common module (DTOs, utils, etc.) -->
<dependency>
    <groupId>com.finpay</groupId>
    <artifactId>common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
Then import in usable classes.
Example in [Auth service -> UserService](./../auth-service/src/main/java/com/finpay/authservice/services/UserService.java)

```java
import com.finpay.common.dto.users.UserDTO;
import com.finpay.common.enums.RoleEnum;

private UserDTO convertToDTO(UserEntity user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setUsername(user.getUsername());
    userDTO.setEmail(user.getEmail());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setRole(RoleEnum.valueOf(user.getRole().getRoleName()));
    userDTO.setLocation(user.getLocation().getPlace());
    return userDTO;
}
```