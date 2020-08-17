# demo-basic-authentication

Spring security has done a lot of magics.  Key entry points to configure this framework

* customer security @Configuration that extends from the `WebSecurityConfigurationAdater`
* UserDetailsService: how and where the **UserDetails** is loaded
* PasswordEncorder: indicating which password encorder is applied. 

The items can be auto-configured by the Spring auto-configuration mechanism. If you don't declare yourown, then default will be plugged in. 
