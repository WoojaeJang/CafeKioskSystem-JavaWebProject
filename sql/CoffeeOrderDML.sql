-- 고객 테이블에 값 저장
INSERT INTO customer values('c1', '1234', 'lee', '010-1234-5678', 5000);
INSERT INTO customer values('c2', 'qwer', 'park', '010-2345-6789',10000);
INSERT INTO customer values('c3', 'asdf', 'kim', '010-3456-7890', 15000);

-- DML

-- 음식 테이블에 값 저장
INSERT INTO food values('f1', 'americano', 4000);
INSERT INTO food values('f2', 'latte', 4500);
INSERT INTO food values('f3', 'mocha', 5000);

-- 지불 수단 테이블에 값 저장
INSERT INTO pay values('p1', 'credit_card');
INSERT INTO pay values('p2', 'samsung_pay');
INSERT INTO pay values('p3', 'point');

-- 주문 테이블에 값 저장해보기()
INSERT INTO coffee_order values(seq_no.nextval, 'c1', 'americano', 4000, 'credit_card', SYSTIMESTAMP, 'check');
INSERT INTO coffee_order values(seq_no.nextval, 'c1', 'americano', 4000, 'credit_card', SYSTIMESTAMP, null);
INSERT INTO coffee_order values(seq_no.nextval, 'c2', 'americano', 4000, 'credit_card', SYSTIMESTAMP, 'check');
INSERT INTO coffee_order values(seq_no.nextval, 'c3', 'americano', 4000, 'credit_card', SYSTIMESTAMP, 'done');


alter session set nls_date_format='yyyy-mm-dd hh:mi:ss';

COMMIT;