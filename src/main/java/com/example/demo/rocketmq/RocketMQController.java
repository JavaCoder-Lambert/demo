package com.example.demo.rocketmq;

import cn.hutool.extra.tokenizer.Result;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.domain.User;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年08月08日 17:09
 */
@RestController
@RequestMapping("/rocketmq")
public class RocketMQController {

    @Autowired
    private MQProducerService mqProducerService;

    @GetMapping("/send")
    public void send() {
        User user = User.builder().userId(1).userName("lizhijiang").password("111").build();;
        mqProducerService.send(user);
    }

    @GetMapping("/sendTag")
    public R<SendResult> sendTag() {
        SendResult sendResult = mqProducerService.sendTagMsg("带有tag的字符消息");
        return R.ok(sendResult);
    }

}

