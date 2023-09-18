## 소개

국회의원과 최신 의안, 의사일정, 발행물을 확인 할 수 있는 안드로이드 앱

## 주요 기능
- 열린 국회 정보 포털의 국회 API 및 공공데이터 포털의 국회관련 API 연동
- 이름, 지역 및 정당별 의원 조회
- 의안명 검색
- 의사 일정 조회
- 발행물 조회

## 기술 스택
 - 언어: Kotlin
 - IDE: Android studio
 - 라이브러리: coroutine, hilt, retrofit2, paging3, room, navigation

## 프로젝트 구조
```
- com
  - myhand
    - nationalassembly
      - data
        - local
          - member
            - db
              - DataMapper.kt
              - MemberDatabase.kt
              - MemberPhotoDao.kt
            - model
              - MemberPhotoModel.kt
            - MemberLocalDataSource.kt
          - remote
            - bill
              - BillRemoteDataSource.kt
            - member
              - MemberRemoteDataSource.kt
            - report
              - library
                - model
                  - LibraryReportResponse.kt
                  - LibraryReportRow.kt
                - LibraryReportApi.kt
              - ReportDataSource.kt
            - schedule
              - ScheduleRemoteDataSource.kt
      - data
        - repository
          - BillRepository.kt
          - BillRepositoryImpl.kt
          - MemberRepository.kt
          - MemberRepositoryImpl.kt
          - ReportRepository.kt
          - ReportRepositoryImpl.kt
          - ScheduleRepository.kt
          - ScheduleRepositoryImpl.kt
      - di
        - AppModule.kt
        - RepositoryModule.kt
      - ui
        - view
          - base
            - WebViewFragment.kt
          - bill
            - BillFragment.kt
            - BillSearchFragment.kt
          - member
            - MemberBillFragment.kt
            - MemberDetailFragment.kt
            - MemberInfoFragment.kt
            - MemberSearchFragment.kt
          - report
            - BudgetFragment.kt
            - ReportBudgetFragment.kt
            - ReportFragment.kt
          - MainActivity.kt
          - MainFragment.kt
      - util
        - Const.kt
        - Extentions.kt
        - LogUtil.kt
      - MyHandNAApplication.kt
```

## 화면
- xml로 구현

| `메인화면` | `국회의원정보` | `의안검색` | `의사일정` | `보고서` |
| :-:| :-: | :-: | :-: | :-: |
| <img width="200" src="https://github.com/jharinn/handgov/assets/67181098/1f80b1fa-787e-476c-ab21-dfee9979dd31"> | <img width="200" src="https://github.com/jharinn/handgov/assets/67181098/1b53d43e-24c1-4296-8ecf-c63035857615"> | <img width="200" src="https://github.com/jharinn/handgov/assets/67181098/f943129a-8360-4747-85e9-f0bcd4c4a1d5"> | <img width="200" src="https://github.com/jharinn/handgov/assets/67181098/82ce10a7-706d-4bd5-b4fa-18adfa9213b7"> | <img width="200" src="https://github.com/jharinn/handgov/assets/67181098/66b67615-b0a2-4156-82b4-38601e4d6e9d"> 
