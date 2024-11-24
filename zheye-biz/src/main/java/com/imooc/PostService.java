package com.imooc;

import com.imooc.dto.post.PostDetailDTO;
import com.imooc.param.post.PostCreateParam;
import com.imooc.param.post.PostUpdateParam;

/**
 * @author wzy
 * @date 2024/11/21 09:47
 */
public interface PostService {

    PostDetailDTO createPost(PostCreateParam createParam);

    PostDetailDTO getDetail(String id);

    PostDetailDTO updateDetail(String id,
                               PostUpdateParam updateParam);

    PostDetailDTO delete(String id);

    PostDetailDTO getPostDetail(Integer id, Integer page, Integer size);
}
