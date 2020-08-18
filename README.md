# demo-basic-authentication

Spring security has done a lot of magics.  Key entry points to configure this framework

* **Customer security Configuration** that extends from the `WebSecurityConfigurationAdater`
* UserDetailsService: how and where the **UserDetails** is loaded
* **PasswordEncorder**: indicating which password encorder is applied. 
* **UserDetails** is an Spring interface, which illustrates the user security attributes

The items can be auto-configured by the Spring auto-configuration mechanism. If you don't declare yourown, then default will be plugged in. 

## Authentication using database

**AuthenticationProvider** is an interface indicating that its impl. provides authentication services. 

For an authentication using a database, **DaoAuthenticationProvider**, depending on two beans. 

**PasswordEncorder** and **UserDetailsService** 

The key is to implement the **UserDetailsService** to access a database, and load the **UserDetails** by its **UserName**. 

## Using custom login/logout form

So far we are using the default login and logout form provided by Spring security. We may also config our own login and logout templates. 
