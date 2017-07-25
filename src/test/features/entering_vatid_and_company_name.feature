Feature: Entering VAT ID and the company name
  In order to avoid errors
  As a Teddysoft employee
  I want to make sure the VAT ID and company name are consistency

  Scenario: Entering VAT ID gets the corresponding company name
    When  I enter the VAT ID "53909614"
    Then  I should see the company name "泰迪軟體科技有限公司"

  Scenario: Entering company name gets the corresponding VAT ID
    When  I enter the company name "泰迪軟體科技有限公司"
    Then  I should see the VAT ID "53909614"

  Scenario Outline: Successful entering VAT ID gets corresponding company name
    When  I enter the company name <companyName>
    Then  I should see the VAT ID <vatID>
    Examples:
      | companyName            |    vatID      |
      |"蒂森電梯股份有限公司"  |  "14070857"   |
      |"鼓勵閣商業顧問有限公司" |  "24940741"  |
      |"百碩工業股份有限公司"  | "97352022"  |
      |"大鵰生技股份有限公司" |  "86831314"  |
      |"王小明公司"           | "查無此統一編號"|


  Scenario Outline: Successful entering company name gets corresponding VAT ID
    When  I enter the VAT ID <vatID>
    Then  I should see the company name <companyName>
    Examples:
      | companyName           | vatID          |
      |"百碩工業股份有限公司"  |   "97352022"  |
      |"蒂森電梯股份有限公司"  |  "14070857"   |
      |"鼓勵閣商業顧問有限公司" |  "24940741"  |
      |"查無此公司"              |  "51909614"  |
      |"統一編號格式錯誤"       |  "12345"      |

