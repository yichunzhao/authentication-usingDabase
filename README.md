# demo-basic-authentication

Spring security has done a lot of magics.  Key entry points to configure this framework

* **customer security Configuration** that extends from the `WebSecurityConfigurationAdater`
* UserDetailsService: how and where the **UserDetails** is loaded
* **PasswordEncorder**: indicating which password encorder is applied. 
* **UserDetails** is an Spring interface, which illustrates the user security attributes

The items can be auto-configured by the Spring auto-configuration mechanism. If you don't declare yourown, then default will be plugged in. 

## authentication using database

**AuthenticationProvider** is an interface indicating its impl. provides authentication services. 

For an authentication using database, using **DaoAuthenticationProvider**, which depends on the following two beans. 

**PasswordEncorder** and **UserDetailsService** 
