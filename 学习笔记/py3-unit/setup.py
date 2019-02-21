#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
python setup.py sdist
打源码包，部署
解压
python setup.py install
python setup.py bdist
"""

import os
from setuptools import find_packages, setup

from src import __version__

# get the dependencies and installs
here = os.path.abspath(os.path.dirname(__file__))
with open(os.path.join(here, 'requirements.txt')) as f:
    all_requirements = f.read().split('\n')

setup(
    name='evo',
    version=__version__,
    license='MIT',
    author='yangshuhua',
    author_email='903403246@qq.com',
    description='python3 evo day',
    url='https://github.com/yangshuhua/evo',
    packages=find_packages(exclude=['tests']),
    include_package_data=True,  # 默认True,MANIFEST.in配置生效
    zip_safe=False,
    install_requires=all_requirements,
    entry_points={
        'console_scripts': [
            'test_run = src.quickly_cmd:run_parse_input',
        ],
    }
)
