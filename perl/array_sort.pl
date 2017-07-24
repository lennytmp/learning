#!/usr/bin/perl

use Data::Dumper;
use strict;
use warnings;

#input: 12,5,3,7
#output: 3,5,7,12

my @arr = (12, 5, 3, 7);

sub sort_num_arr {
  my $arr = shift;
  
  return join(':', sort {$a <=> $b} @$arr);
}

sub sort_num_arr2 {
  my $arr = shift;
  
  for (my $i = 0; $i < @$arr; $i++) {
    for (my $j = $i + 1; $j < @$arr; $j++) {
      if ($arr->[$i] >= $arr->[$j]) {
        my $tmp = $arr->[$i];
        $arr->[$i] = $arr->[$j];
        $arr->[$j] = $tmp;
      }
    }
  }
  
  return join(':', @$arr);
}

print sort_num_arr(\@arr), "\n";
print sort_num_arr2(\@arr), "\n";
