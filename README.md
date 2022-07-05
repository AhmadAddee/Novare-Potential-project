# ATMInterface

## IMPORTANT!
When running the application, there are some mockup users in the system. When testing, either create a new user or login with an existing one (for example adam:1234).

## About
This project contains a small ATM/BANK simulator written in Java.

* View the release page to download the jar file. Or click [HERE](https://github.com/Novare-Potential/ATMInterface/tags) to download the jar file
* Project description/requierments can be found [here](https://docs.google.com/document/d/1iOUQwnx9qJl4euzYNNb8Taq0tCa4gK1a)

## What the app provides
There is one user-role, a user. A user can do the following:

1. Login to the application (the password is stored as a sha256 hash)
1. Signup
1. Basic ATM operations
    * Withdraw money
    * Deposit money
    * View balance
1. Basic Bank operations
    * Update password
    * Update username
    * Transfer money to another user
    
  
## System explanation
*NOTE:* This class diagram is a simplification of the system; It only shows key features of each class and how it is used and interacts with other classes. 
![](http://www.plantuml.com/plantuml/png/dLN1Sjis4BtZAtPo2LKdwjrjCucTIsVirXr5kTGzGEIIH0q27EpIgiRJVoy0SaM98rrwedRny7RVmmBwJAm33vrLfTL4yAYDWpTr0w_ULD35wC7xlbYfVzM4kDFk9I80FiAMC7ozWo5-wAUSUJ34Fs-Go7Zt2NJ9vXMda2BW1gO0h6-CApOSZ6ioWTCTVe0fqST2Un-gLTwpyzwYTa2cSTl-wgPwiFQtwon9KARm0nVI1etVBA_NvOjkC1DdWQFUhOH9twBsb7sQ2XqhZlbbOm2nZF3EcuJRE0RIbgTmTs3yyozeDRKvC_jG-gxN0SM8CYR3D_o7dqxqpeNabDuWqyWXfUodyUZ6_Csej45Ety9KluVj93CIBZe8g1apRZw6sV_-UYD_31WEyBPGL67loN2XEpyuNYrMzuRRAkZzOldLuFuf-DfOB4vpTzfgLyuv3jfHZK7yOlyXUttEDlILPXJ5WdMubzGvKBm-0PxRFm-K3Dd9J6rECiVPjFcYdMPg4uYBfHs6ldg7XPYRj62eTOdmfUJ8IgUiOgsmp6R-RnklE7RPdqjjRjdOv8yI47mStn8rZQgNK0Oc0_AQKkBT3oad0CacOAQVOt55gVHonSJCbH-x64xWOvo0IZdF2EnxyBKWRk5BlZAZ65qoPJ-1MmGw46CNVmO_DEspoqavUh_x6qjUmwyCXlBAz_l4--xn-_sxirApfbZCWTuHfqAPfCuxtwSEteDCrxurPNkX69JQfMRN4xt5cXD54dSBto9-b_pmpXwWrQwoc1iRXmw8iOVY8RtIFsvCuuP-1SykmX967W02lIoQkycbTvYgLya83xeiaKR-W9Jxc3sJ3dUUsNTnJOxi5kwzOsqSWROsxP0LqFaSSg4UG-t31PQ469K-7hq694B7HjieknJsPvUuEr-XXQIHM3DUR59EAOtASSW8VibchiT_jPMYjLpTw8V58WpE6TUiqisajPL_L50OYoLQfJx7dqDd_mC0)
