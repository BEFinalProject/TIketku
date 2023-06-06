package com.arj.tiketkufinalproject.Service;

import com.arj.tiketkufinalproject.Model.UsersEntity;
import com.arj.tiketkufinalproject.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository R;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<UsersEntity> getAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return R.findAll(pageable);
    }
    public UsersEntity getById(UUID id_user){
        return R.findById(id_user).get();
    }

    public UsersEntity updateUser(UsersEntity param) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        UsersEntity userExist =  R.findById(param.getUuid_user()).get();
        userExist.setEmail(param.getEmail());
        userExist.setPassword(passwordEncoder.encode(param.getPassword()));
        userExist.setGender(param.getGender());
        userExist.setFull_name(param.getFull_name());
        userExist.setPhone(param.getPhone());
        userExist.setModified_at(currentDateTime);
        return R.save(userExist);
    }

    public UsersEntity addUsers(UsersEntity param) {
        Optional<UsersEntity> userExist = R.findByEmail(param.getEmail());
        if (userExist.isPresent()) {
            throw new RuntimeException("Username " + param.getEmail() + " Sudah Ada");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        param.setUuid_user(generateUUID());
        param.setCreated_at(currentDateTime);
        param.setPassword(passwordEncoder.encode(param.getPassword()));
        param.setRoles("ROLE_USER");
        return R.save(param);

    }

//    public List<UsersEntity> addMultipleUsers(List<UsersEntity> param) {
//        List<UsersEntity> list = new ArrayList<>();
//
//        for(UsersEntity user : param){
//            Optional<UsersEntity> userExsist = R.findById(user.getUid_user());
//            if(userExsist.isPresent()){
//                throw new RuntimeException("User ID " +user.getUid_user() + " Sudah Ada");
//            }
//            else{
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//                list.add(R.save(user));
//            }
//        }
//        return list;
//    }


    public UsersEntity delUser(UUID param){
        UsersEntity delete = R.findById(param).get();
        R.deleteById(param);
        return delete;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    public UsersEntity resetPassword(String email, String newPassword) {
        UsersEntity user = R.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User dengan username " + email + " tidak ditemukan"));

        // Memeriksa apakah password baru sama dengan password sebelumnya
        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new RuntimeException("Password baru tidak boleh sama dengan password sebelumnya");
        }

//        // Memeriksa apakah password sebelumnya sesuai
//        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
//            throw new RuntimeException("Password sebelumnya tidak sesuai");
//        }

        // Mengubah password baru dan menyimpan perubahan
        user.setPassword(passwordEncoder.encode(newPassword));
        return R.save(user);
    }

}
