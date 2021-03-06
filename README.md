# Spring-Authentication-Authorization

Spring security has done a lot of magics.  Key entry points to configure this framework

* **Customer security Configuration** that extends from the `WebSecurityConfigurationAdater`
* UserDetailsService: how and where the **UserDetails** is loaded
* **PasswordEncorder**: indicating which password encorder is applied. 
* **UserDetails** is an Spring interface, which illustrates the user security attributes

The items can be auto-configured by the Spring auto-configuration mechanism. If you don't declare yourown, then default will be plugged in. 

## Elements involved

* User-password authentication: One of the most common ways to authenticate a user by validating username and password.
* Reading the username and passowrd: reading name password froma Customised login page
* Storage mechanisms : User security details is stored in a Relational database.
* UserDetailsService : Core interface which loads user-specific security details. 
* PasswordEncoder : Service interface for encoding password. Normally we use BCryptPasswordEncoder.
* AuthenticationProvider : Indicate a class that can process Authentication. 

## Authentication using database

**AuthenticationProvider** is an interface indicating that its impl. provides authentication services. 

For an authentication using a database, **DaoAuthenticationProvider**, depending on two beans. 

**PasswordEncorder** and **UserDetailsService** 

The key is to implement the **UserDetailsService** to access a database, and load the **UserDetails** by its **UserName**. 

## Using custom login/logout form

Compared with the basic authentication, form-based allows to define a logout precess, but the basic cannot.  

So far we are using the default login and logout form provided by Spring security. We may also config our own login and logout templates. 

**AuthenticationProvider** provides an authentication service, according to username and password. How is it used by the template? 

The magic that linking the login form with the **AuthenticationProvider** is the input field name, i.e. username and password. 

*turn on login/logout form*

`httpSecurity.loginPage` must be a post method, so `httpSecurity.successForwardUrl` need to be post method too. Post forword Post. 

## SecurityContext

It is the place to store the details of who is authenticatd.
It is the heart of Spring security authentication model, and contains the SecurityContext.

Using the helper class `SecurityContextHelper` to access `SecurityContext`.

A `SecuityContextHolder` contains a `SecurityContext`, which cantains an `Authentication` consisting of `Principle`(user), `Credential`(pwd), Authorities(permissions).
By default the `SecurityContextHolder` uses a ThreadLocal to store a `SecurityContext`, meaning that the `SecurityContext` is always availiable to methods in the same thread, even if the `SecurityContext` is not passed around as an argument to those methods.

`SecurityContext` is stored in a `Threadlocal`, so that methods that carried out in the same thread may access `SecurityContext` via the helper class. We may access the principal, the credential and authorities via the `SecurityContext`.

Once a user successfully sign in, `SecurityContext` is ready in the **ThreadLocal**. `Principal` and `Authentication` can be injected in the request handler method as an argument. 

## Scecurity Exceptions

Two types of exceptions, i.e. **AccessDeniedException** and **AuthenticationException**. It is a top level wrapper, for instance, **UserNameNotFoundException** will be wrapped in the **AuthenticationException**. When an exception is thrown, Spring security will understand it as a login failure, landing on the loginFailure url.    

SecurityFilterChain contains an `ExceptionTranslationFilter`, which translates the Security exceptions into http responses. 

## Authorize Request Method Handlers

Enable Spring method security globally: 

````
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
````

for instance: Authorize methods

````
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
````

* prePostEnabled property enables Spring Security pre/post annotations
* securedEnabled preoperty enables @Secured annotation
* jsr250Enabled property enables @RoleAllowed annotation

The benifit to use prePost: it supports SpEL expressions, fx: predict(Relational >< etc) or logic(or, and etc) 

https://www.baeldung.com/spring-security-method-security






