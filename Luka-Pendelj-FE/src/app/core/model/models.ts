export interface Organization{
  organizationId: number,
  organizationCode: string,
  active: true,
  organizationType: OrganizationType,
  name: string,
  address: string,
  phone: string,
  email: string

}

export interface OrganizationType{
  orgTypeId: number,
  name: string
}
