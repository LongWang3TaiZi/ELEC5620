package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.UserDTO;
import com.elec5620.intelligentfinancial.model.Favorite;
import com.elec5620.intelligentfinancial.model.User;
import com.elec5620.intelligentfinancial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic for admin management.
 */
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;
    private final AdminRepository adminRepository;
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public AdminService(UserRepository userRepository, CustomerRepository customerRepository,
                        BankRepository bankRepository, AdminRepository adminRepository, FavoriteRepository favoriteRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.bankRepository = bankRepository;
        this.adminRepository = adminRepository;
        this.favoriteRepository = favoriteRepository;
    }

    /**
     * The getAllUserDetail method returns all users details.
     *
     * @return          userDTOS
     */
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUserDetail() {
        // Returns a collection of addresses corresponding to the mail.
        Collection<User> users = userRepository.findAll();
        if (users == null) {
            return null;
        }
        List<UserDTO> userDTOS = users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
        return userDTOS;
    }

    /**
     * The findUserById method obtains a User object based on the corresponding userId and returns it to the UserDTO object.
     *
     * @param userId    the ID of the user
     * @return          UserDTO
     */
    @Transactional(readOnly = true)
    public UserDTO findUserById(Long userId) {
        // Returns the User object in the database, given the UserId.
        User user = userRepository.getReferenceById(userId);
        if (user == null) {
            return null;
        }
        return new UserDTO(user);
    }

    /**
     * The EditUserInfo method allows admin to update the user's information by userId.
     *
     * @param userId    the ID of user
     * @param userName  the name of user
     * @param role      the role of user
     * @return          UserDTO
     */
    @Transactional
    public UserDTO EditUserInfo(Long userId, String userName, String role) {
        // Finds the user object in the corresponding database based on the UserId.
        User user = userRepository.getReferenceById(userId);
        if (user == null) return null;

        user.setUsername(userName);
        user.setRole(role);
        userRepository.save(user);

        return new UserDTO(user);
    }

    /**
     * The DeleteUser method allows admin to delete the user by userId.
     *
     * @param userId    the ID of user
     * @return          UserDTO
     */
    @Transactional
    public UserDTO DeleteUser(Long userId) {
//        // Finds the user object in the corresponding database based on the UserId.
//        User user = userRepository.getReferenceById(userId);
//        if (user == null) return null;
//
////        if (Objects.equals(user.getRole(), "bank")){
////            bankRepository.delete(user.getBank());
////        } else if (Objects.equals(user.getRole(), "customer")){
////            customerRepository.delete(user.getCustomer());
////        } else if (Objects.equals(user.getRole(), "admin")){
////            adminRepository.delete(user.getAdmin());
////        }
// Finds the user object in the corresponding database based on the UserId.
        User user = userRepository.getReferenceById(userId);
        if (user == null) return null;

        // 如果用户是 customer，删除关联的 favorite 记录
        if ("customer".equals(user.getRole()) && user.getCustomer() != null) {
            List<Favorite> favorites = favoriteRepository.findByCustomerId(user.getId());
            favoriteRepository.deleteAll(favorites);
        }

        // 删除用户及其关联的实体
        userRepository.delete(user);

        return new UserDTO(user);

    }


}
