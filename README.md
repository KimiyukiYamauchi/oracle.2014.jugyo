## 演習の環境設定手順

1. 「[Oracleインスタントクライアントのダウンロード](http://www.oracle.com/technetwork/jp/topics/index-099943-ja.html)から以下をダウンロードし、展開。<br />
「instantclient_12_1」ディレクトリが作成されていることを確認
	* instantclient-basic-linux.x64-12.1.0.2.0.zip
	* instantclient-sqlplus-linux.x64-12.1.0.1.0.zip<br /><br />
1. 「非同期IO(libio)」のインストール<br />
$ sudo aptitude install libaio1<br /><br />
1. 「リードラインラッパー(rlwrap)」のインストール<br />
$ sudo aptitude install rlwrap<br /><br />
1. 「[oracle.2014](https://github.com/KimiyukiYamauchi/oracle.2014.git)」を「git clone」
<br />$ git clone https://github.com/KimiyukiYamauchi/oracle.2014.git (任意のディレクトリ名)<br /><br />
2. リモートリポジトリ「origin」を別の名前に変える
<br />$ git remote rename origin  (任意の別名)<br /><br />
3. 各自のGithubに演習をアップするためのリポジトリを作成<br /><br />
4. 上で作成したリモートリポジトリに「origin」と名前をつける
<br />$ git remote add origin  (各自のリモートリポジトリのURL)<br /><br />
5. ローカルリポジトリをリモートのpushする
<br />$ git push -u origin master<br /><br />
6. Oracleサーバへの接続確認
	1. 接続のためのスクリプトを作成<br />
$ vi sqlplus.sh<br >
で、ファイルを開き、以下を入力。<br />
LD_LIBRARY_PATH=/home/yamauchi/instantclient_12_1<br />
PATH=/home/yamauchi/instantclient_12_1:$PATH<br />
NLS_LANG=JAPANESE_JAPAN.AL32UTF8<br />
export LD_LIBRARY_PATH PATH NLS_LANG<br />
export no_proxy=localhost,172.16.40.4<br />
rlwrap sqlplus hr/hr@172.16.40.4:1521/db11<br />
	2. 作成したスクリプトを実行し、接続できること<br />
$ ./sqlplus.sh<br /><br />
6. 演習環境の設定
	1. 演習用ユーザで接続するためのスクリプト作成
		1. 上で作成したスクリプトをコピー<br />
$ cp sqlplus.sh s99999.sh<br /><br />
		2. 「ユーザ名=>学籍番号、パスワード=>password」に変更<br />
$ vi s99999.sh<br /><br />
		2. 作成したスクリプトで接続<br />
$ ./s99999.sh<br /><br />
		3. 演習用のテーブルを作成するためのスクリプトを実行<br />
SQL> @create_tbl_oracle.sql<br /><br />
		4. 以下のSELECT文を実行し、テーブルが作成できていることを確認<br />
また、それぞれのテーブルに対して、SELECT文を実行し、データがちゃんと入っていることを確認<br />
SQL> select table_name from user_tables;<br />
SQL> select tname from tab;<br />
			* ORDERS
			* ORD_DETAILS
			* SALGRADES
			* EMPLOYEES
			* PRODUCTS
			* CUSTOMERS
			* DEPARTMENTS
6. 以降は以下の操作

	* 演習を作成し、講師提出する場合、

		1. ローカルリポジトリをリモートにpushする 
<br />$ git push<br /><br />
		2. 講師に演習を作成した旨連絡

	* ひな形などの最新を入手する場合、

		1. 「[oracle.2014](https://github.com/KimiyukiYamauchi/oracle.2014.git)」を「git pull」
<br />$ git pull -u (自分がつけた別名) master<br /><br />

## 演習問題

1. 2014/10/02

	1. employees表のempno列、ename列を取り出すスクリプト(141002-1.sql)
	2. employees表のすべての列を取り出すスクリプト(141002-2.sql)
	3. employees表の表構造を表示するスクリプト(141002-3.sql)
	4. employees表のename列、sal列、sal列に100000を足したものを12倍した列
<br />を取り出すスクリプト(141002-4.sql)
	5. employees表のempno、ename、sal、sal列に100000を足したものを12倍した列<br />
を取り出す、この時、それぞれに社員番号、社員名、給与、年収の別名<br />
をつけるスクリプト(141002-5.sql)
