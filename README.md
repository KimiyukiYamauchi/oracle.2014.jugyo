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
1. cloneしたリポジトリに移動<br /><br />
2. リモートリポジトリ「origin」を別の名前に変える
<br />$ git remote rename origin  oracle.2014<br /><br />
3. 各自のGithubに演習をアップするためのリポジトリを作成<br /><br />
4. 上で作成したリモートリポジトリに「origin」と名前をつける
<br />$ git remote add origin  (各自のリモートリポジトリのURL)<br /><br />
5. ローカルリポジトリをリモートのpushする
<br />$ git push -u origin master<br /><br />
6. Oracleサーバへの接続確認
	1. 接続のためのスクリプトを作成<br />
$ vi sqlplus.sh<br >
で、ファイルを開き、以下を入力。<br />
この時、「instantclient_12_1」ディレクトリのパスは各自の環境に<br />
沿って、記述すること<br /><br />
LD_LIBRARY_PATH=/home/yamauchi/instantclient_12_1<br />
PATH=/home/yamauchi/instantclient_12_1:$PATH<br />
NLS_LANG=JAPANESE_JAPAN.AL32UTF8<br />
export LD_LIBRARY_PATH PATH NLS_LANG<br />
export no_proxy=localhost,172.16.40.4<br />
echo -n "ユーザ名："<br />
read user<br />
stty -echo<br />
echo -n "パスワード："<br />
read pass<br />
stty echo<br />
rlwrap sqlplus $user/$pass@172.16.40.4:1521/db11<br /><br />
	2. 作成したスクリプトを実行し、接続できること<br />
$ ./sqlplus.sh<br />
ユーザ名：hr<br />
パスワード：hr (パスワードの入力はエコーバックしないので注意！)<br />
=> これで接続できること！ <br /><br />
6. 演習環境の設定
	1. 演習用のアカウントでサーバに接続<br />
$ ./sqlplus.sh<br />
ユーザ名：(学籍番号)<br />
パスワード：password (パスワードの入力はエコーバックしないので注意！)<br />
=> 各自のアカウントで接続される <br /><br />
	1. 演習用のテーブルを作成するためのスクリプトを実行<br />
SQL> @create_tbl_oracle.sql<br /><br />
	1. 以下のSELECT文を実行し、テーブルが作成できていることを確認<br />
また、それぞれのテーブルに対して、SELECT文を実行し、データがちゃんと入っていることを確認<br />
SQL> select table_name from user_tables;<br />
SQL> select tname from tab;

		- ORDERS
		- ORD_DETAILS
		- SALGRADES
		- EMPLOYEES
		- PRODUCTS
		- CUSTOMERS
		- DEPARTMENTS

6. 以降は以下の操作

	* 演習を作成し、講師提出する場合、

		1. 「master」branchに切り替え。
<br />$ git checkout master <br /><br />
		1. 演習の作業を行うbranchを作成し、それにcheckout
<br />$ git checkout -b (演習リポジトリ名) <br /><br />
		1. 演習を行い、成果物をadd, commitで、リポジトリに登録 
<br />$ git add -A
<br />$ git commit -m "コメント"<br /><br />
		1. 演習の成果物を「master」branchにmerge
<br />$ git checkout master 
<br />$ git merge (演習リポジトリ名) <br /><br />
		1. ローカルの「master」をGithubに上げる
<br />$ git push <br /><br />
		1. 講師に演習を作成した旨連絡
			* yamauchi@std.it-college.ac.jp

	* ひな形などの最新を入手する場合、

		1. 「[oracle.2014](https://github.com/KimiyukiYamauchi/oracle.2014.git)」を「git pull」
<br />$ git pull oracle.2014 master<br /><br />

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

2. 2014/10/07

	1. employees表のjob列を重複を排除して取り出すスクリプト(141007-1.sql)
	2. employees表でenameが「佐藤」のempno、ename、deptno列を取り出す<br />
スクリプト(141007-2.sql)
	3. employees表でhiredateが「2006年10月21日」のempno、ename、hiredate列を<br />
取り出すスクリプト(141007-3.sql)
	4. employees表でsalが「30万以上」のempno、ename、sal列を取り出すスクリプト<br />
(141007-4.sql)
	5. employees表でsalが「20万以上かつ30万以下」のempno、ename、sal列を取り出す<br />
スクリプト(141007-5.sql)

3. 2014/10/08

	1. employees表でdeptnoが「10または20」であるempno、ename、deptno列を取り出す<br />
スクリプト(141008-1.sql)
	2. employees表でdeptnoが「10でも20でもない」empno、ename、deptno列を取り出す<br />
スクリプト(141008-2.sql)
	3. employees表でsalが「20万より少ないまたは30万より多い」場合のempno、ename、sal列<br />
を取り出すスクリプト(141008-3.sql)
	4. products表でpnameに「A4」を含む場合のprodno、pname、price列を取り出す<br />
スクリプト(141008-4.sql)
	5. employees表でcommが「NULL」のempno、ename、sal、comm列を取り出す<br />
スクリプト(141008-5.sql)

4. 2014/10/09

	1. employees表でsalが「30万以上」かつdeptnoが「30」であるempno、ename、<br />
sal、deptno列を取り出すスクリプト(141009-1.sql)
	2. employees表からempno、ename、sal、deptno列を取り出す。この時、salの多い順<br />
にソートする。但し、salが同額の場合にはdeptnoの小さい順でソートするスクリプト<br />
(141009-2.sql)
	3. employees表でdeptnoが置換変数で受け取った値と等しいempno、ename、sal、deptno列<br />
を取り出すスクリプト(141009-3.sql)
	4. products表でpnameに「100%」を含むprodno、pname、price列を取り出すスクリプト<br />
(141009-4.sql)
	5. employees表でcommが「NULL」でないempno、ename、sal、comm列を取り出す<br />
スクリプト(141009-5.sql)

1. 2014/10/14

	1. employees表でyomiが「TAKAHASHI」であるempno, ename, yomi列を小文字で格納されている<br />
ため比較時に取得データを大文字に変換すること(141014-1.sql)
	1. employees表からyomi列を取り出し、これを先頭のみ大文字に変換する。さらに、<br />
「@std.it-college.ac.jp」と結合するスクリプト(141014-2.sql)
	1. 文字列「Oracle Server」の２文字目から３文字取得および同じ文字列の２文字目<br />
以降を取得するスクリプト(141014-3.sql)
	1. 文字列「Oracle Server」の「Server」を「Master」に変換するスクリプト(141014-4.sql)
	1. employees表からempno, ename, yomi列及びyomiの長さを取得するスクリプト(141014-5.sql)

1. 2014/10/15

	1. 以下のように日付の表示書式と表示言語を変更<br />
alter session set nls_date_format = 'DD-MON-RR';<br />
alter session set nls_date_language = 'AMERICAN';<br /> 
その後、employees表からdeptnoが「10」のename, hiredate, を取り出すスクリプト(141015-1.sql)<br />
「[NLS関連初期化パラメータ](http://www.shift-the-oracle.com/config/nlsparameter.html)」
	1. 以下のように日付の表示書式と表示言語を元に戻す<br />
alter session set nls_date_format = 'RR-MM-DD';<br />
alter session set nls_date_language = 'JAPANESE';<br /> 
その後、employees表からdeptnoが「10」のename, hiredate, を取り出すスクリプト(141015-2.sql)<br />
	1. employees表からdeptnoが「10」のename, hiredate, hiredateの90日後、hiredateの90日前<br />
を表示するスクリプト(141015-3.sql)
	1. employees表からhiredateから今日までの経過した月数を小数点以下を切り捨てて表示する<br />
スクリプト(141015-4.sql)
	1. 今月の最終日を表示するスクリプト(141015-5.sql)

1. 2014/10/16

	1. 現在日時を「2014-10-11 12:16:10」の書式で文字列に変換して表示するスクリプト<br />
(141016-1.sql)
	1. 現在日時を「2014年10月11日(土曜日)」の書式で文字列に変換して表示するスクリプト<br />
(141016-2.sql)
	1. 以下のように日付の表示書式と表示言語を変更<br />
alter session set nls_date_format = 'DD-MON-RR';<br />
alter session set nls_date_language = 'AMERICAN';<br /> 
その後、employees表からenameとhiredate（「15TH of October」の書式で変換）<br />
を取り出すスクリプト(141016-3.sql)<br />
	1. 以下のように日付の表示書式と表示言語を元に戻す<br />
alter session set nls_date_format = 'RR-MM-DD';<br />
alter session set nls_date_language = 'JAPANESE';<br /> 
その後、文字列「2011年01月01日」を日付値に変換するスクリプト(141016-4.sql)<br />
	1. 文字列「￥5,000,000」を数値の「5000000」に変換し、12で割るスクリプト<br />
(141016-5.sql)

1. 2014/10/21

	1. employees表からename, sal, comm, sal+comm（commがnullの場合はNVL関数を使用して0と見なす）を表示するスクリプト(141021-1.sql)
	1. employees表からename, sal, comm, sal+comm（commがnullの場合はNVL2関数を使用して0と見なす）を表示するスクリプト(141021-2.sql)
	1. employees表からename, sal, comm, sal+comm（commがnullの場合はCOALESCE関数を使用して0と見なす）を表示するスクリプト(141021-3.sql)
	1. employees表からenameとCASE式を使って、deptnoが10の時はsalを1.1倍、20の時は1.2倍、それ以外はsalを表示する。この時、計算結果の別名として、NEW_SALを表示する(141021-4.sql)
	1. employees表からenameとDECODE関数を使って、deptnoが10の時はsalを1.1倍、20の時は1.2倍、それ以外はsalを表示する。この時、計算結果の別名として、NEW_SALを表示する(141021-5.sql)

1. 2014/10/22

	1. employees表からsalの平均と合計を表示するスクリプト(141022-1.sql)
	1. employees表からdeptno毎のdeptno、人数、salの平均を表示する。この時、deptnoの昇順で、ソートする(141022-2.sql)
	1. employees表からdeptno、job毎のdeptno、job、人数、salの平均を表示する。この時、deptnoの昇順で、ソートする。(141022-3.sql)
	1. employees表からdeptno毎のsalの平均の最大値を表示するスクリプト(141022-4.sql)
	1. employees表からdeptnoとjobの組み合わせ毎のdeptno、job、人数、salの平均を表示する。但し、人数が２人以上の組み合わせのみ表示する。この時、deptnoの昇順で、ソートする。(141022-5.sql)

1. 2014/10/23

	1. employees表とdepartments表を結合(deptno)し、empno, ename, dnameを表示するスクリプト(141023-1.sql)
	1. employees表とdepartments表を結合(deptno)し、deptnoが10または20のempno, ename, dnameを表示するスクリプト(141023-2.sql)
	1. orders表、customers表およびemployees表の３つの表を結合(orders.custno, customers.custno, orders.salesman_no, employees.empno)し、ordno, date_ordered, cname, enameを表示するスクリプト(141023-3.sql)
	1. employees表とsalgrades表を非等価結合(employees.sal, salgrades.losal, salgrades.hisal)し、empno, ename, sal, gradeを表示するスクリプト(141023-4.sql)
	1. employees表を自己結合(empno, mgr)して、empno, enameおよび上司のempno, enameを表示するスクリプト(141023-5.sql)
	1. （応用問題）上記の自己結合で、外部結合を用い、「社長」のデータも取り出せる様に修正したスクリプト(141023-6.sql)

1. 2014/10/28

	1. USING句を使用し、employees表とdepartments表を結合(deptno)し、empno, ename, deptno, dnameを表示するスクリプト(141028-1.sql)
	1. (準備)employees表に１件データを追加する<br />
insert into employees(empno, ename, deptno) values(1015, '山口', null);<br />
employees表とdepartments表を結合(deptno)し、empno, ename, deptno, dnameを表示する。この時、employees表のdeptnoがnullのデータも取り出せるスクリプト(141028-2.sql)
	1. employees表とdepartmentsを結合(deptno)し、empno, ename, deptno, dnameを表示する。この時、employees表でだれも割り当られていないdepartments表の部門も取り出せるスクリプト(141028-3.sql)
	1. employees表とdepartmentsを結合(deptno)し、empno, ename, deptno, dnameを表示する。この時、employees表のdeptnoがnullのデータ、およびemployees表でだれも割り当られていないdepartments表の部門も取り出せるスクリプト(141028-4.sql)
	1. employees表とdepartments表をクロス結合し、empno, ename, dnameを表示するスクリプト(141028-5.sql)

1. 2014/10/29

	1. employees表からempnoが「1003」のsal（employees表から副問い合わせ)以上のempno, ename, salを表示するスクリプト(141029-1.sql)
	1. employees表からdnameが「営業」（departments表から副問い合わせ）のempno, ename, deptnoを表示するスクリプト(141029-2.sql)
	1. employees表からsalがempnoの「1003」以上（employees表から副問い合わせ）、かつdnameが「営業」（departments表から副問い合わせ)のempno, ename, sal, deptnoを表示するスクリプト(141029-3.sql)
	1. employees表とdepartments表を結合(deptno)し、deptno, dname毎のsalの平均が全体の平均（employees表から副問い合わせ）以上であるdeptno, dname, avg(sal)を表示するスクリプト(141029-4.sql)
	1. employees表を自己結合(empno, mgr)し、enameが「山田」または「伊藤」の上司(mgr)(employees表から副問い合わせ)のempno, enameおよび上司のenameを表示するスクリプト(141029-5.sql)

1. 2014/10/30

	1. employees表からdeptnoが「10」または「20」のdeptno, empno, enameを表示するselect文とdeptnoが「20」または「30」のdeptno, empno, enameを表示するselect文の問い合わせ結果を連結し、重複した行を排除して戻すスクリプト(141030-1.sql)
	1. employees表からdeptnoが「10」または「20」のdeptno, empno, enameを表示するselect文とdeptnoが「20」または「30」のdeptno, empno, enameを表示するselect文の問い合わせ結果を連結し、重複した行も含めて戻すスクリプト(141030-2.sql)
	1. employees表からdeptnoが「10」または「20」のdeptno, empno, enameを表示するselect文とdeptnoが「20」または「30」のdeptno, empno, enameを表示するselect文の問い合わせ結果を連結し、共通した行だけ戻すスクリプト(141030-3.sql)
	1. employees表からdeptnoが「10」または「20」のdeptno, empno, enameを表示するselect文とdeptnoが「20」または「30」のdeptno, empno, enameを表示するselect文の問い合わせ結果を連結し、１つめのselect文の結果のうち、２つ目問い合わせ結果にない行を戻すスクリプト(141030-4.sql)
	1. employees表からdeptnoが「10」または「20」のdeptno, empno, enameを表示するselect文とdeptnoが「20」または「30」のdeptno, empno, enameを表示するselect文の問い合わせ結果を連結し、重複した行を排除して戻す。この時、empnoの昇順で並べて表示するスクリプト(141030-5.sql)

1. 2014/11/04

	1. departments表にdeptno「50」、dname「教育」、loc「大手町」で、１行追加し、select文を実行し、正しく追加できていることを確認。その後、追加したデータを削除し、削除できていることを確認(141104-1.sql)
	1. departments表にdeptno「60」、dname「経理」、locはnullで、１行追加し、select文を実行し、ただしく追加できていること確認。その後、追加したデータを削除し、削除できていることを確認（141104-2.sql)
	1. employees表にempno, ename, hiredateを置換変数で入力、その他はnullで１行追加しselect文を実行し、正しく追加できていること確認。その後、追加したデータを削除し、削除できていることを確認(141104-3.sql)
	1. （事前の操作）departments表からdept_copy表を作成。<br />
create table dept_copy as select * from departments where 0 = 1;<br />
departments表からselect文を使用してデータを取得し、deptno「deptno+1」、dname「dname」、loc「loc」データを追加し、select文を実行し、正しく追加できていることを確認(141104-4.sql)

1. 2014/11/05

	1. （事前の操作）employees表からemp_copy表を作成。<br />
create table emp_copy as select * from employees;<br />
deptnoがnullのデータについて、deptno「10」を設定し、正しく更新出来ていることを確認(141105-1.sql)
	1. emp_copy表のempnoが「1012」のデータについて、ename「林」、depto「20」を設定（更新前は「吉田」、「30」）し、正しく更新できていることを確認(141105-2.sql)
	1. emp_copy表のempnoが「1013」および「1014」のデータについて、deptno「null」を設定し、正しく更新できていることを確認(141105-3.sql)
	1. emp_copy表のempnoがemployees表のenameが「加藤」に等しいデータ(副問い合わせ)について、jobにemployees表のempnoが「1010」のjob(副問い合わせ)、salにemployees表のempnoが「1010」のsal(副問い合わせ)をそれぞれ設定し、正しく更新出来ていることを確認(141105-4.sql)

1. 2014/11/06

	1. 以下の操作を行うスクリプト(20141106-1.sql)
		1. dept_copy表にdeptno「50」、dname「教育」、loc「大手町」を追加
		1. dept_copy表にdeptno「60」、dname「システム」、loc「横浜」を追加
		1. select文で、上記の追加が正常に行われていることを確認
		1. 上記の追加の取消処理
		1. select文で、上記の取消処理が正常に行われていることを確認		
		1. dept_copy表にdeptno「50」、dname「教育」、loc「大手町」を追加
		1. dept_copy表にdeptno「60」、dname「システム」、loc「横浜」を追加
		1. select文で、上記の追加が正常に行われていることを確認
		1. 上記の追加の確定処理
		1. 上記の追加の取消処理
		1. select文で、上記の確定処理後の取消処理が無効であることを確認		
	1. 以下の操作を行うスクリプト(20141106-2.sql)
		1. dept_copy表にdeptno「70」、dname「海外」、loc「浦添」を追加
		1. セーブポイントを設定
		1. dept_copy表にdeptno「80」、dname「製造」、loc「うるま」を追加
		1. select文で、上記のdeptno「70」、「80」の両方の追加が正常に行われていることを確認
		1. セーブポイントまで、取消処理
		1. select文で、deptno「70」のみ追加されていること(deptno「80」の追加が取り消されていること)
