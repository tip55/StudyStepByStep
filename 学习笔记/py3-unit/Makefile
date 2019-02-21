# 定义变量,引用该变量的方式是$(PYTHON)
PYTHON:=$(shell which python3)

# 构造没有规则的终极目标,并指定可执行文件作为依赖,make或make all时会执行后边所依赖的可执行文件
# all: clean python
all: python

# target这一个或多个的目标文件依赖于prerequisites中的文件,其生成规则定义在command中
# prerequisites中如果有一个以上的文件比target文件要新的话,command所定义的命令就会被执行
# 特殊：target没有定义prerequisites,那么目标每次被make时,command都不会被执行
# 关键字.PHONY可以解决这问题,告诉make目标是"伪目标",使make为生成这个目标将其规则执行一次
.PHONY: clean python test flake8

python: setup.py requirements.txt
	pip install virtualenv
	echo "\n Creating python virtual environment......\n"
	virtualenv -p $(PYTHON) env
	echo "\n Use python virtual environment to install required packages......\n"
	# pip -help//pip install -help
	# Install a project in editable mode (i.e. setuptools "develop mode") from a local project path or a VCS url.
	env/bin/pip install -e .
	mkdir -p webspider/log
	touch webspider/log/spider_log.txt

test: flake8
	env/bin/nosetests -vd

flake8:
	env/bin/flake8

# 清空目标文件的规则
# 每个Makefile中都应该写一个清空目标文件的规则,这不仅便于重编译,也很利于保持文件的清洁
# clean从来都是放在文件的最后,放在文件的开头会被当做默认目标
clean:
	-rm -rf env cover *eggs *.egg-info *.egg webspider/log
	@find . -type f -name "*.py[co]" -delete
	@find . -type d -name "__pycache__" -delete


