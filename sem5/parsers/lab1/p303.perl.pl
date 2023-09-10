undef $/;
my $input = do {<>};

# $input =~ s/^\s+//;
# $input =~ s/\s+$//;
# $input =~ s/( *+\n){2,}+/\n\n/g;
# $input =~ s/ +/ /g;
# $input =~ s/\n +/\n/g;
# $input =~ s/ +\n/\n/g;

# $input =~ s/\s*//g;
while ($input =~ s/<a[^<>]*?href\s*=\s*"([^"<>]*?)"([^<>]*)>//) {
	$url = $1;
	if ($url =~ /(([^:\/\#]+):\/\/)?((?<authority>[^\/\?\#]*))?([^\?\#]*)(\?([^\#]*))?(\#(.*))?/) {
		$authority = $+{authority};
		$authority =~ s/^([^<>]+@)*\s*(\w[^:\#]*)[:\#]?.*/\2/;
    	push @hosts, $authority;
	}
}

$last = "";
foreach $host (sort @hosts) {
    if (!($last =~ $host)) { 
        print $host;
        print "\n";
        $last = $host;
    }
}