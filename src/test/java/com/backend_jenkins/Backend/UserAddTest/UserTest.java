package com.backend_jenkins.Backend.UserAddTest;

import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest
{
//     @Autowired
//     private UserRepository userRepository;

//     @Test
//     void Should_add_user_successfully() {
//     User user = new User(99888776,"mazenhaouari@gmail.com","mazen");
//     User saveduser = userRepository.save(user);
//         Assertions.assertNotNull(saveduser);
//     }


// //    @Test
// //    void shouldNotAddUserWithWrongEmail() {
// //        User user = new User(99888776, "mazenhagmail.com", "mazen");
// //        Exception exception = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
// //            userRepository.save(user);
// //        });
// //        String expectedMessage = "could not execute statement";
// //        String actualMessage = exception.getMessage();
// //        Assertions.assertTrue(actualMessage.contains(expectedMessage));
// //    }

//     @Test
//     public void Should_retreive_user_with_id_successufuly(){
//         User user = new User(99888776,"Test","mazen");
//         userRepository.save(user);
//         User fuser = userRepository.findById(user.getId()).get();
//         Assertions.assertNotNull(fuser);
//     }

//     @Test
//     public void Should_retreive_all_user_successufuly(){
//         List<User> Listuser = userRepository.findAll();
//         Assertions.assertNotNull(Listuser);
//     }

//     @Test
//     public void Update_user_successufuly(){
//         User user = new User(99888776,"Test","mazen");
//         userRepository.save(user);
//         User fuser = userRepository.findById(user.getId()).get();
//         fuser.setNom("aftertest");
//         fuser.setEmail("aftertest@gmail.com");
//         User updateduser = userRepository.save(fuser);
//         Assertions.assertNotNull(fuser.getNom());
//         Assertions.assertNotNull(fuser.getEmail());
//     }

//     @Test
//     public void Delete_user_successufuly(){
//         User user = new User(99888776,"Test","mazen");
//         userRepository.save(user);

//         userRepository.deleteById(user.getId());

//         Optional<User> Userreturn = userRepository.findById(user.getId());
//         Assertions.assertTrue(Userreturn.isEmpty());
//     }

}
