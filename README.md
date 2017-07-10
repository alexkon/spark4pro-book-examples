## Download and prepare test data for chapter 2

```bash
mkdir linkage && cd linkage
wget http://bit.ly/1Aoywaq
mv 1Aoywaq donation.zip
unzip donation.zip
unzip 'block_*.zip'
rm -rf `find . | grep / | grep -v block.*.csv`
```