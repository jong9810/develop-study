package hello.springmvc.basic.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mapping/users")
@RestController
public class MappingClassController {
    /**
     * 회원 목록 조회: GET /mapping/users
     * 회원 등록: POST /mapping/users
     * 회원 조회: GET /mapping/users/id1
     * 회원 수정: PATCH /mapping/users/id1
     * 회원 삭제: DELETE /mapping/users/id1
     */

    @GetMapping
    public String user() {
        return "get users";
    }

    @PostMapping
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }

}
