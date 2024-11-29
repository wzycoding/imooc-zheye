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
        return postService.createPost(createParam);
    }

    @GetMapping("/{id}")
    public PostDetailDTO getDetail(@PathVariable(name = "id") Long id) {
        return postService.getDetail(id);
    }

    @PatchMapping("/{id}")
    public PostDetailDTO updateDetail(@PathVariable(name = "id") Long id,
                                      @RequestBody PostUpdateParam updateParam) {
        return postService.updateDetail(id, updateParam);
    }

    @DeleteMapping("/{id}")
    public PostDetailDTO delete(@PathVariable(name = "id") Long id) {
        return postService.delete(id);
    }
}
