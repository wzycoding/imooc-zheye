package com.imooc.post;

import com.imooc.PostService;
import com.imooc.dto.post.PostDetailDTO;
import com.imooc.param.post.PostCreateParam;
import com.imooc.param.post.PostUpdateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {
    @Resource
    private PostService postService;

    @PostMapping("/")
    public PostDetailDTO createPost(@RequestBody PostCreateParam createParam) {
        return new PostDetailDTO();
    }

    @GetMapping("/{id}")
    public PostDetailDTO getDetail(@PathVariable(name = "id") String id) {
        return new PostDetailDTO();
    }

    @PatchMapping("/{id}")
    public PostDetailDTO updateDetail(@PathVariable(name = "id") String id,
                                      @RequestBody PostUpdateParam updateParam) {
        return new PostDetailDTO();
    }

    @DeleteMapping("/{id}")
    public PostDetailDTO delete(@PathVariable(name = "id") String id) {
        return new PostDetailDTO();
    }

    @GetMapping("columns/{id}")
    public PostDetailDTO getPostDetail(@PathVariable(name = "id") Integer id,
                                       @RequestParam("page") Integer page,
                                       @RequestParam("size") Integer size) {
        return new PostDetailDTO();
    }
}
