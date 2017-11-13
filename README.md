# Hibernate Test Case Templates [HHH-12087](https://hibernate.atlassian.net/browse/HHH-12087)

To run testcase you need to modify hibernate-test-case-HHH-12087\orm\hibernate-orm-5\src\test\resources\META-INF\persistence.xml file and set Oracle database connection properties for both persistant units.

Persistant unit "templatePU" demonstrate issue of Hibernate dialect implementation for Oracle paging requests

Persistant unit "templatePUFixed" uses my own fixed implementation of Oracle dialect (hibernate-test-case-HHH-12087\orm\hibernate-orm-5\src\test\java\org\hibernate\bugs\hhh12087\Oracle10gDialectFixed.java) and according test demonstrate successes.