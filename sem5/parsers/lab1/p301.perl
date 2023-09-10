undef $/;
my $input = do {<>};

# $input =~ s/^\s+//;
# $input =~ s/\s+$//;
# $input =~ s/( *+\n){2,}+/\n\n/g;
# $input =~ s/ +/ /g;
# $input =~ s/\n +/\n/g;
# $input =~ s/ +\n/\n/g;

# $input =~ s/\s*//g;
while ($input =~ s/<\s*a.*href\s*=\s*"(?<scheme>(.+?:)\/\/)?(?<host>\w+.*?)[\"\/\:].*>//) {
	#$url = $1;
	#if ($url =~ /(\w+?:\/\/|\/\/)?((?<authority>[^\/\?\#]*))?/) {
		#$authority = $+{authority};
		#$authority =~ s/^([^<>]+@)*\s*(\w[^:\#]*)[:\#]?.*/\2/;
    	push @hosts, $+{host};
	#}
}

$last = "";
foreach $host (sort @hosts) {
    if (!($last =~ $host)) { 
        print $host;
        print "\n";
        $last = $host;
    }
}