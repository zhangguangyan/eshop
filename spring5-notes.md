## Runtime stack

1. servlet container 3.0 and lower 
2. servlet container 3.1 and upper(tomcat 8.5/jetty)
3. netty

Stack 1 does not support reactive programming

Both stack 2 and 3 support reactive programming, which is based on reactive stream

## Web Programming model

1. Blocking (traditional SpringMVC and annotation only)
2. Non-blocking/reactive programming (servlet 3.1 and netty)


### Reactive programming style

1. annotation: controller
2. functional: router and handler 

### Database access programming

1. asynchronous driver
2. traditional jdbc driver


