## 技术选型
- 测试框架：Junit5
- 断言: Hamcrest
- 接口测试: Rest-Assured
- yaml读取与生成： Jackson
- har 解析: Har-reader
- 测试报告：Allure-junit5

## 功能
1，通过配置yaml用例文件就可以完成基本接口测试

2，可以将har文件直接转成api对象

3，运行用例，生成可视化测试报告


## api yaml 举例
```
name: tokenhelper
actions:
  #ApiObjectMethodModel
  getToken:
    formalParam : ["corpid","corpsecret"]
    get: https://qyapi.weixin.qq.com/cgi-bin/gettoken
    query:
      corpid: ${corpid}
      corpsecret: ${corpsecret}
```

## testcase yaml 举例
```
#ApiTestCaseModel
name: getToken
description: 获取Token方法测试用例
steps:
  - api: tokenhelper
    action: getToken
    actualParameter: ["ww5ef451bf006xxxxx","EcEIog2OJ8AtO7PDaqt_yuVZS3NeYF3kcko9xxxxxx"]
    saveGlobal:
      accesstoken: access_token
    asserts:
      - actual: errcode
        matcher: equalTo
        expect: 0
        reason: 'getToken错误码校验01！'
      - actual: errcode
        matcher: equalTo
        expect: 2
```


## 测试报告截图
[![图片](https://z3.ax1x.com/2021/07/03/RR9d5q.md.png)](https://imgtu.com/i/RR9d5q)
