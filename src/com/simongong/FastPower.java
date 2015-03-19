package com.simongong;

/*
Calculate the a^n % b where a, b and n are all 32bit integers.

Example
For 2^31 % 3 = 2

For 100^1000 % 1000 = 0

Challenge
O(logn)

思路：
对于计算型的处理，通常都是分解因式
(a ^ b) % p = (a % p * b % p) % p
a^n % b = (a^(n/2) * a^(n/2) * a^(n%2)) % b = ((a^(n/2) * a^(n/2)) % b * a^(n%2) % b) % b = ((a^(n/2) % b * a^(n/2) % b) * a^(n%2) % b) % b
 */
public class FastPower {

}
